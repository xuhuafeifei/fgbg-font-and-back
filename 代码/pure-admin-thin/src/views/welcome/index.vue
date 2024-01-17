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
  <!-- <div class="flex justify-center relative top-1/3"> -->
  <!-- This is an example component -->
  <div class="relative gap-4 p-4 mb-8 rounded-lg bg-white shadow-lg">
    <div class="relative flex gap-4">
      <img
        src="https://icons.iconarchive.com/icons/diversity-avatars/avatars/256/charlie-chaplin-icon.png"
        class="relative rounded-lg -top-8 -mb-4 bg-white border h-20 w-20"
        alt=""
        loading="lazy"
      />
      <div class="flex flex-col w-full">
        <div class="flex flex-row justify-between">
          <p
            class="relative text-xl whitespace-nowrap truncate overflow-hidden"
          >
            COMMENTOR
          </p>
          <a class="text-gray-500 text-xl" href="#"
            ><i class="fa-solid fa-trash"
          /></a>
        </div>
        <p class="text-gray-400 text-sm">20 April 2022, at 14:88 PM</p>
      </div>
    </div>
    <p class="-mt-4 text-gray-500">
      Lorem ipsum dolor sit amet consectetur adipisicing elit. <br />Maxime
      quisquam vero adipisci beatae voluptas dolor ame.
    </p>
  </div>
  <!-- </div> -->
</template>
