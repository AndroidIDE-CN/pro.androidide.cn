
import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useFunctionStore = defineStore('function', () => {
  const isUseAgrOpen = ref(false); // 使用协议
  const isPriAgrOpen = ref(false); // 隐私政策
  const isResAgrOpen = ref(false); // 免责声明
  const isUpdeteOpen = ref(false); // 更新日志
  const isHisVerOpen = ref(false); // 历史版本
  const isThanksOpen = ref(false); // 特别鸣谢
  const isContacOpen = ref(false); // 联系我们

  const isMobileHeadMore = ref(false);

  const setIsUseAgrOpen = (value) => isUseAgrOpen.value = value;
  const setIsPriAgrOpen = (value) => isPriAgrOpen.value = value;
  const setIsResAgrOpen = (value) => isResAgrOpen.value = value;
  const setIsUpdateOpen = (value) => isUpdeteOpen.value = value;
  const setIsHisVerOpen = (value) => isHisVerOpen.value = value;
  const setIsThanksOpen = (value) => isThanksOpen.value = value;
  const setIsContacOpen = (value) => isContacOpen.value = value;

  const setIsMobileHeadMore = (value) => isMobileHeadMore.value = value;

  return { isUseAgrOpen, isPriAgrOpen, isResAgrOpen, isUpdeteOpen, isHisVerOpen, isThanksOpen, isContacOpen, isMobileHeadMore, setIsUseAgrOpen, setIsPriAgrOpen, setIsResAgrOpen, setIsUpdateOpen, setIsHisVerOpen, setIsThanksOpen, setIsContacOpen, setIsMobileHeadMore }
})
