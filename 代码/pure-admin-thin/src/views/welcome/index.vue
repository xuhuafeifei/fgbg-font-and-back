<script setup lang="ts">
import { ref, onMounted, nextTick } from "vue";
import { UnitEntity, getData } from "@/api/tree.ts";
import TreeAddOrUpdate from "./treeAddOrUpdate.vue";
defineOptions({
  name: "Welcome"
});

const tableData = ref<Array<UnitEntity>>();

onMounted(() => {
  getData().then(res => {
    console.log(res);
    if (res.code === 0) {
      tableData.value = res.data;
    }
  });
});

const dialogVisible = ref(false);

// 引用子组件
const treeAddOrUpdateRef = ref();

// 新增/修改 都可以使用该方法
const addOrUpdate = data => {
  console.log(data);
  dialogVisible.value = true;
  // nextTick保证treeAddOrUpdateRef能够引用到子组件
  nextTick(() => {
    // 调用子组件暴露的init方法, 设置数据
    treeAddOrUpdateRef.value.init(data);
  });
};
</script>

<template>
  <el-button type="primary" @click="addOrUpdate(null)">新增</el-button>
  <el-table
    :data="tableData"
    style="width: 100%; margin-bottom: 20px"
    row-key="id"
    border
  >
    <el-table-column prop="id" label="序号" sortable />
    <el-table-column prop="unit" label="单位" sortable />
    <el-table-column
      fixed="right"
      header-align="center"
      align="center"
      width="150"
      label="操作"
    >
      <template v-slot="scope">
        <el-button type="text" size="small" @click="addOrUpdate(scope.row)"
          >修改</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <!--引用方法-->
  <tree-add-or-update v-if="dialogVisible" ref="treeAddOrUpdateRef" />
</template>
