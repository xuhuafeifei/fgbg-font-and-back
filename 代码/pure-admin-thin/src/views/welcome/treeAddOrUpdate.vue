<script setup lang="ts">
import {
  UnitEntity,
  LabelVo,
  getLabelTree,
  getNodeById,
  saveOrUpdate
} from "@/api/tree";
import { ElMessage } from "element-plus";
import { ref, onMounted, reactive, defineExpose } from "vue";

onMounted(() => {
  getLabelTree().then(res => {
    console.log(res);
    if (res.code === 0) {
      data.value = res.data;
    }
  });
});

const dialogVisible = ref(false);

let form = reactive(new UnitEntity());
const title = ref("新增表单");

// 定义init方法, 让父组件调用
const init = data => {
  console.log(data);
  // 清除数据
  valueStrictly.value = "";
  form = reactive(new UnitEntity());
  if (data) {
    form = data;
    title.value = "编辑表单";
    if (form.pid !== null) {
      getNodeById(form.pid).then(res => {
        console.log(res);
        valueStrictly.value = res.data.value;
      });
    }
  } else {
    title.value = "新增表单";
  }
  console.log(form);
  dialogVisible.value = true;
};

// 暴露方法
defineExpose({ init });

// 提交表单
const submit = () => {
  console.log(form);
  console.log(valueStrictly.value);
  saveOrUpdate(form).then(res => {
    if (res.code === 0) {
      ElMessage.success("提交成功");
    } else {
      ElMessage.error("提交失败");
    }
    location.reload();
  });
};

const handleCheckChange = (data: LabelVo, checked, indeterminate) => {
  console.log(data);
  console.log(checked);
  if (checked) {
    form.pid = data.id;
  }
};

const valueStrictly = ref();

const data = ref<Array<LabelVo>>();
</script>

<template>
  <el-dialog v-model="dialogVisible" :title="title">
    <el-form :model="form">
      <el-form-item label="单元">
        <el-input v-model="form.unit" />
      </el-form-item>
      <el-form-item label="上级单位">
        <!-- <el-input v-model="form.pid" /> -->
        <!-- 树形下拉框 -->
        <el-tree-select
          v-model="valueStrictly"
          :data="data"
          :render-after-expand="false"
          show-checkbox
          check-strictly
          check-on-click-node
          @check-change="handleCheckChange"
          style="width: 240px"
        />
      </el-form-item>
    </el-form>
    <el-button @click="submit">提交</el-button>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
