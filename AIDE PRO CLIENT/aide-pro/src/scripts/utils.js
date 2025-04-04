
import axiostool from "./axiostool"
import siteConfig from "../../config/config"
import { useFunctionStore } from "@/stores/function"
import { useApplicationStore } from "@/stores/application"

const openOtherPage = (link) => open(link);

export default { axiostool, siteConfig, useFunctionStore, useApplicationStore, openOtherPage }