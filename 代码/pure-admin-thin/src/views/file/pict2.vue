<script setup lang="ts">
import Child from "./component/child.vue";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { uploadBatch } from "/src/api/file.ts";

const fileList = ref();
const submit = () => {
  console.log(fileList.value);
  // 封装formData
  const data = new FormData();
  // forEach遍历的时fileList.value, 所有element不需要.value去除代理
  fileList.value.forEach(element => {
    data.append("imageList", element.raw);
  });
  uploadBatch(data).then(res => {
    console.log(res);
    if (res.code === 0) {
      ElMessage.success("上传成功");
    } else {
      ElMessage.error("上传失败: " + res.msg);
    }
  });
};
</script>

<template>
  <Child v-model:fileList="fileList" />
  <el-button @click="submit">提交</el-button>
</template>

<style lang="scss" scoped></style>
