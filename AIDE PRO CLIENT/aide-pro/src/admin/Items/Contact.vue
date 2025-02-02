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
        url: null,
        name: null,
        description: null,
    });
    const tableData = reactive([]);
    const dialogStatus = ref(false);
    onMounted(async () => await getTableData());

    const currentChange = async (page) => {
        pages.value = page;
        await getTableData();
    }

    const getTableData = async () => {
        await axiostool.sendGetRequest(`admin/contact/query?page=${pages.value}&limit=${limit.value}`).then(async (response) => {
            if (response.code !== 200) {
                ElMessage({ message: response.message, type: 'error', plain: true});
            } else {
                total.value = response.data.total;
                tableData.splice(0, tableData.length, ...response.data.items);
            }
        });
    }

    const addItem = () => {
        formModel.url = "";
        editModel.value = 1;
        formModel.id = null;
        formModel.name = "";
        dialogStatus.value = true;
        formModel.description = "";
    }

    const editItem = (item) => {
        editModel.value = 0;
        formModel.id = item.id;
        formModel.url = item.url;
        dialogStatus.value = true;
        formModel.name = item.name;
        formModel.description = item.description;
    }

    const deleteItem = async (id) => {
        await axiostool.sendPostRequest("admin/contact/delete", { id: id }).then(async (response) => {
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
        await axiostool.sendPostRequest(`admin/contact/${ editModel.value ? 'add' : 'update' }`, formModel).then(async (response) => {
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
            <el-button type="primary" size="large" :icon="Plus" @click="addItem">新增方式</el-button>
            <el-button type="info" size="large" :icon="Refresh" @click="getTableData">刷新列表</el-button>
        </div>

        <el-table :data="tableData" border style="width: 100%; margin: 20px 0;">
            <el-table-column prop="name" label="联系名称"/>
            <el-table-column prop="url" label="联系链接"/>
            <el-table-column prop="description" label="联系描述"/>
            <el-table-column label="更多操作" width="240px">
                <template #default="scope">
                    <el-button type="primary" style="margin: 0 10px;" @click="editItem(scope.row)">修改</el-button>
                    <el-button type="danger" style="margin: 0 10px;" @click="deleteItem(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination class="pagination" background layout="prev, pager, next" :total="total" :hide-on-single-page="true" @currentChange="currentChange" v-if="tableData.length > 0"/>

        <el-dialog v-model="dialogStatus" :title="`${editModel ? '新增' : '修改'}方式`" width="520" align-center>
            <el-form :model="formModel" label-width="auto">
                <el-form-item label="联系名称">
                    <el-input v-model="formModel.name" placeholder="请输入联系名称" clearable/>
                </el-form-item>

                <el-form-item label="联系链接">
                    <el-input v-model="formModel.url" placeholder="请输入联系链接" clearable/>
                </el-form-item>

                <el-form-item label="联系描述">
                    <el-input v-model="formModel.description" placeholder="请输入联系描述" clearable/>
                </el-form-item>
            </el-form>
            <el-button type="primary" style="width: 100%; height: 40px; font-size: 16px; margin-top: 10px;" @click="sendEditRequest">{{ `确认${editModel ? '新增' : '修改'}` }}</el-button>
        </el-dialog>
    </div>
</template>