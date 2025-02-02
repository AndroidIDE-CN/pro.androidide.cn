
import fs from "fs"
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from 'node:url'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import viteCompression from 'vite-plugin-compression'
import { VantResolver } from '@vant/auto-import-resolver'
import { VueHooksPlusResolver } from '@vue-hooks-plus/resolvers'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

const base64SetPlugin = async (limit = 4096) => {
  return {name: "base64-set-plugin",async transform(_, id) {if (!/\.(png|jpg|svg)$/.test(id)) return;const stat = await fs.promises.stat(id);if (stat.size > limit) return;const buffer = await fs.promises.readFile(id);const base64 = buffer.toString("base64");const dataUrl = `data:image/${id.split(".").pop()};base64,${base64}`;return {code: `export default ${JSON.stringify(dataUrl)}`}}};
}

export default defineConfig({
  build: {
    rollupOptions: {treeshake: true,output: {
      manualChunks(id) {if (id.includes('node_modules')) {return id.toString().split('node_modules/')[1].split('/')[0].toString();}},
      chunkFileNames: 'static/js/[name]-[hash].js',entryFileNames: 'static/js/[name]-[hash].js',assetFileNames: 'static/[ext]/[name]-[hash].[ext]',commonjsOptions: {requireReturnsDefault: 'namespace'}
    }}, chunksizewarninglimit: 1500, 
    terseroptions: {compress: {drop_console: true,drop_debugger: true}},
    minify: true,brotliSize: false,assetsDir: 'assets',assetsInlineLimit: 5 * 1024 * 1024,
  },
  css: {modules: {generateScopedName: '[name]-[hash:base64:5]'}},
  entry: '/src/application.js',output: {manualChunks(id) {if (id.includes('node_modules')) {return 'vendor';}}},resolve: {alias: {'@': fileURLToPath(new URL('./src', import.meta.url))}},
  server: {host: '0.0.0.0',hot: {reload: true,rerender: true},routes: {beforeEnter(req, res, next) {if (/\.js$/.test(req.path)) res.redirect('/');else next();if (/\.md$/.test(req.path)) res.redirect('/');else next();}}},
  plugins: [vue(),base64SetPlugin(),AutoImport({resolvers: [ElementPlusResolver(),VantResolver(),VueHooksPlusResolver()]}),Components({ resolvers: [ElementPlusResolver(),VantResolver()], dirs: ['src'], extensions: ['vue','jsx'], dts: 'aide.d.ts'  }), viteCompression({ verbose: true, disable: false, deleteOriginFile: false, threshold: 5120, algorithm: 'gzip', ext: '.gz' })], 
});
