<script setup lang="ts">
import { CommunicateEntity, list, save } from "/src/api/communicate.ts";
import { ElMessage } from "element-plus";
import { ref, onMounted } from "vue";

const input = ref("");

const chatList = ref<Array<CommunicateEntity>>();

onMounted(() => {
  getData();
});

const getData = () => {
  list().then(res => {
    console.log(res);
    chatList.value = res.data;
  });
};

/** 模拟获取当前登录用户id */
const getUserId = () => {
  return 1;
};

/** 模拟获取当前登录用户username */
const getUserName = () => {
  return "测试用户";
};

const submit = (pid?: Number, replyUserId?: Number) => {
  const entity = new CommunicateEntity();
  entity.content = input.value;
  entity.pid = pid;
  entity.userId = getUserId();
  entity.replyUserId = replyUserId;

  save(entity).then(res => {
    if (res.code === 0) {
      ElMessage.success("提交成功");
      getData();
    }
  });
};
</script>

<template>
  <div style="border: 1px solid #ccc; margin-top: 10px">
    <el-input v-model="input" textarea style="height: 200px" />
    <el-button @click="submit()">提交</el-button>
    <el-divider />
    <div v-for="item in chatList" :key="item.id">
      <!-- This is an example component -->
      <div
        class="relative grid grid-cols-1 gap-4 p-4 mb-8 border rounded-lg bg-white shadow-lg"
      >
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
                {{ getUserName() }}
              </p>
              <a class="text-gray-500 text-xl" href="#"
                ><i class="fa-solid fa-trash"
              /></a>
            </div>
            <p class="text-gray-400 text-sm">{{ item.createTime }}</p>
          </div>
        </div>
        <p class="-mt-4 text-gray-500">{{ item.content }}</p>
        <div>
          <el-popover trigger="click">
            <template #reference>
              <el-button
                style="margin-right: 16px; float: right"
                link
                type="primary"
                >回复</el-button
              >
            </template>
            <el-input v-model="input" />
            <el-button @click="submit(item.id, item.userId)">提交</el-button>
          </el-popover>
        </div>
      </div>
      <!-- 回复列表 -->
      <div v-for="subItem in item.children" :key="subItem.id">
        <div
          class="relative grid grid-cols-1 gap-4 p-4 mb-8 border rounded-lg bg-white shadow-lg"
          style="margin-left: 100px"
        >
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
                  {{ subItem.userId }} 回复
                  <span style="color: cornflowerblue">
                    @{{ getUserName() }}</span
                  >
                </p>
                <a class="text-gray-500 text-xl" href="#"
                  ><i class="fa-solid fa-trash"
                /></a>
              </div>
              <p class="text-gray-400 text-sm">{{ subItem.createTime }}</p>
            </div>
          </div>
          <p class="-mt-4 text-gray-500">{{ subItem.content }}</p>
          <div>
            <el-popover trigger="click">
              <template #reference>
                <el-button
                  style="margin-right: 16px; float: right"
                  link
                  type="primary"
                  >回复</el-button
                >
              </template>
              <el-input v-model="input" />
              <el-button @click="submit(item.id, item.userId)">提交</el-button>
            </el-popover>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
