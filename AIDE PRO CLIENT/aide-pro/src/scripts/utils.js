
// import developer from "./developer"
import axiostool from "./axiostool"
import siteConfig from "../../config/config"
// import DisableDevtool from 'disable-devtool'
import { useFunctionStore } from "@/stores/function"
import { useApplicationStore } from "@/stores/application"
// if (import.meta.env.MODE === 'production') DisableDevtool(developer);

const openOtherPage = (link) => open(link);

export default { axiostool, siteConfig, useFunctionStore, useApplicationStore, openOtherPage }