<script setup lang="ts">
import "@wangeditor/editor/dist/css/style.css";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { IEditorConfig } from "@wangeditor/editor";
import { shallowRef, ref, onBeforeUnmount } from "vue";
import { baseUrlApi } from "@/api/utils";

// 初始化 MENU_CONF 属性
const editorConfig: Partial<IEditorConfig> = {
  MENU_CONF: {}
};
const mode = "default";

// 编辑器实例，必须用 shallowRef，重要！
const editorRef = shallowRef();

const handleCreated = editor => {
  console.log("created", editor);
  editorRef.value = editor; // 记录 editor 实例，重要！
};

// 绑定数据
const valueHtml = ref("");

// 组件销毁时，也及时销毁编辑器，重要！
onBeforeUnmount(() => {
  const editor = editorRef.value;
  if (editor == null) return;

  editor.destroy();
});

// 配置上传地址
editorConfig.MENU_CONF["uploadImage"] = {
  // form-data fieldName ，默认值 'wangeditor-uploaded-image'
  fieldName: "image",
  server: baseUrlApi("fullText/file/upload"),
  // 小于该值就插入 base64 格式（而不上传），默认为 0
  base64LimitSize: 5 * 1024 // 5kb
};

const handleChange = editor => {
  // TS 语法
  console.log("content", editor.getHtml());
};
</script>

<template>
  <div style="border: 1px solid #ccc; margin-top: 10px">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editorRef"
      :mode="mode"
    />
    <Editor
      style="height: 500px; overflow-y: hidden"
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="handleCreated"
      @onChange="handleChange"
    />
  </div>
</template>

<style lang="scss" scoped></style>
