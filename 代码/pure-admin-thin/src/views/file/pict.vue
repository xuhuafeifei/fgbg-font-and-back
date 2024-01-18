<template>
  <el-upload
    v-model:file-list="fileList"
    list-type="picture-card"
    multiple
    :auto-upload="false"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove"
  >
    <el-icon><Plus /></el-icon>
  </el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
  <el-button @click="submit">提交</el-button>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { Plus } from "@element-plus/icons-vue";
import { uploadBatch } from "/src/api/file.ts";
import type { UploadProps } from "element-plus";
import { ElMessage } from "element-plus";

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

const fileList = ref();

const dialogImageUrl = ref("");
const dialogVisible = ref(false);

const handleRemove: UploadProps["onRemove"] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
};

const handlePictureCardPreview: UploadProps["onPreview"] = uploadFile => {
  dialogImageUrl.value = uploadFile.url!;
  dialogVisible.value = true;
};
</script>
