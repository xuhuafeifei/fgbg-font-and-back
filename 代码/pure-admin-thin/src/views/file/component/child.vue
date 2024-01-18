<template>
  <el-upload
    v-model:file-list="localFileList"
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
</template>

<script lang="ts" setup>
import { ref, watch } from "vue";
import { Plus } from "@element-plus/icons-vue";
import type { UploadProps } from "element-plus";

// 定义数据
const props = defineProps({
  fileList: {
    type: Array,
    default: () => []
  }
});

// 将父组件的数据拆解为子组件数据
const localFileList = ref(props.fileList);

// 监听localFileList, 跟新父组件数据
watch(
  localFileList,
  newValue => {
    emits("update:fileList", newValue);
  },
  {
    deep: true
  }
);

// 定义组件事件, 跟新fileList
const emits = defineEmits(["update:fileList"]);

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
