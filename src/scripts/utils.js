
import apiConfig from "./config"
import developer from "./developer"
import axiostool from "./axiostool"
import siteConfig from "../../config/config"
import DisableDevtool from 'disable-devtool'
import { useFunctionStore } from "@/stores/function"
import { useApplicationStore } from "@/stores/application"
if (import.meta.env.MODE === 'production') DisableDevtool(developer);

export default { apiConfig, axiostool, siteConfig, useFunctionStore, useApplicationStore }