<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable no-undef -->

<script setup>
    import axiostool from '@/scripts/axiostool'
    import { onMounted, reactive, ref } from 'vue'
    import { Plus, Refresh } from '@element-plus/icons-vue'

    const pages = ref(1);
    const total = ref(0);
    const limit = ref(10);
    const editModel = ref(0);
    const formModel = reactive({
        id: null,
        size: null,
        updateLog: null,
        versionName: null,
        versionCode: null,
        downloadUrl: null,
    });
    const tableData = reactive([]);
    const dialogStatus = ref(false);
    onMounted(async () => await getTableData());

    const currentChange = async (page) => {
        pages.value = page;
        await getTableData();
    }

    const getTableData = async () => {
        await axiostool.sendGetRequest(`admin/historical/query?page=${pages.value}&limit=${limit.value}`).then(async (response) => {
            if (response.code !== 200) {
                ElMessage({ message: response.message, type: 'error', plain: true});
            } else {
                total.value = response.data.total;
                tableData.splice(0, tableData.length, ...response.data.items);
            }
        });
    }

    const addItem = () => {
        editModel.value = 1;
        formModel.size = null;
        formModel.updateLog = "";
        dialogStatus.value = true;
        formModel.downloadUrl = "";
        formModel.versionCode = "";
        formModel.versionName = "";
    }

    const editItem = (item) => {
        editModel.value = 0;
        formModel.id = item.id;
        dialogStatus.value = true;
        formModel.size = item.size;
        formModel.updateLog = item.updateLog;
        formModel.downloadUrl = item.downloadUrl;
        formModel.versionCode = item.versionCode;
        formModel.versionName = item.versionName;
    }

    const deleteItem = async (id) => {
        await axiostool.sendPostRequest("admin/historical/delete", { id: id }).then(async (response) => {
            if (response.code !== 200) {
                ElMessage({ message: response.message, type: 'error', plain: true});
            } else {
                ElMessage({ message: response.message, type: 'success', plain: true});
                await getTableData();
            }
        });
    }

    const sendEditRequest = async () => {
        if (formModel.name === "" || formModel.url === "" || formModel.icon === "" || formModel.description === "") return ElMessage({ message: "请填写完整信息", type: 'warning', plain: true});
        await axiostool.sendPostRequest(`admin/historical/${ editModel.value ? 'add' : 'update' }`, formModel).then(async (response) => {
            if (response.code !== 200) {
                ElMessage({ message: response.message, type: 'error', plain: true});
            } else {
                ElMessage({ message: response.message, type: 'success', plain: true});
                await getTableData();
            }
        });
        dialogStatus.value = false;
    }
</script>

<template>
    <div class="content-frame frame-friends">
        <div class="action-box">
            <el-button type="primary" size="large" :icon="Plus" @click="addItem">新增版本</el-button>
            <el-button type="info" size="large" :icon="Refresh" @click="getTableData">刷新列表</el-button>
        </div>

        <el-table :data="tableData" border style="width: 100%; margin: 20px 0;">
            <el-table-column prop="versionCode" label="版本号码"/>
            <el-table-column prop="versionName" label="版本名称"/>
            <el-table-column prop="size" label="版本大小"/>
            <el-table-column prop="downloadUrl" label="下载链接"/>
            <el-table-column prop="updateLog" label="版本日志"/>
            <el-table-column label="更多操作" width="240px">
                <template #default="scope">
                    <el-button type="primary" style="margin: 0 10px;" @click="editItem(scope.row)">修改</el-button>
                    <el-button type="danger" style="margin: 0 10px;" @click="deleteItem(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination class="pagination" background layout="prev, pager, next" :total="total" :hide-on-single-page="true" @currentChange="currentChange" v-if="tableData.length > 0"/>

        <el-dialog v-model="dialogStatus" :title="`${editModel ? '新增' : '修改'}版本`" width="520" align-center>
            <el-form :model="formModel" label-width="auto">
                <el-form-item label="版本号码">
                    <el-input v-model="formModel.versionCode" placeholder="请输入版本号码" clearable/>
                </el-form-item>

                <el-form-item label="版本名称">
                    <el-input v-model="formModel.versionName" placeholder="请输入版本名称" clearable/>
                </el-form-item>

                <el-form-item label="版本大小">
                    <el-input v-model="formModel.size" placeholder="请输入版本大小" clearable/>
                </el-form-item>

                <el-form-item label="下载链接">
                    <el-input v-model="formModel.downloadUrl" placeholder="请输入下载链接" clearable/>
                </el-form-item>

                <el-form-item label="版本日志">
                    <el-input type="textarea" v-model="formModel.updateLog" placeholder="请输入版本日志"/>
                </el-form-item>
            </el-form>
            <el-button type="primary" style="width: 100%; height: 40px; font-size: 16px; margin-top: 10px;" @click="sendEditRequest">{{ `确认${editModel ? '新增' : '修改'}` }}</el-button>
        </el-dialog>
    </div>
</template>