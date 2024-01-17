<script setup lang="ts">
import { CommunicateEntity, list, save } from "/src/api/communicate.ts";
import { ElMessage } from "element-plus";
import { ref, onMounted } from "vue";

const input = ref("");

const replyInput = ref("");

const chatList = ref<Array<CommunicateEntity>>();

const submit = (replyUserId?: Number, pid?: Number) => {
  const entity = new CommunicateEntity();
  entity.replyUserId = replyUserId;
  entity.content = input.value;
  entity.pid = pid;
  console.log(entity);

  save(entity).then(res => {
    if (res.code === 0) {
      ElMessage.success("提交成功");
      getData();
    } else {
      ElMessage.error("提交失败: " + res.msg);
    }
  });
};

onMounted(() => {
  getData();
});

const getData = () => {
  list().then(res => {
    console.log(res);
    chatList.value = res.data;
  });
};

// 模拟获取用户信息(一般用户信息会在登陆时, 存储在浏览器本地)
const getUser = (userId: Number) => {
  return "测试人员";
};
</script>

<template>
  <div style="border: 1px solid #ccc; margin-top: 10px">
    <el-input v-model="input" textarea style="height: 200px" />
    <el-button @click="submit()">提交</el-button>
    <el-divider />
    <div v-for="item in chatList" :key="item.id">
      <!-- This is an example component -->
      <div class="relative gap-4 p-6 rounded-lg mb-8 bg-white border">
        <div class="relative flex gap-4">
          <img
            src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
            class="relative rounded-lg -top-8 -mb-4 bg-white border h-20 w-20"
            alt=""
            loading="lazy"
          />
          <div class="flex flex-col w-full">
            <div class="flex flex-row justify-between">
              <p
                class="relative text-xl whitespace-nowrap truncate overflow-hidden"
              >
                {{ getUser(item.id) }}
              </p>
              <a class="text-gray-500 text-xl" href="#"
                ><i class="fa-solid fa-trash"
              /></a>
            </div>
            <p class="text-gray-400 text-sm">{{ item.createTime }}</p>
          </div>
        </div>
        <p class="-mt-4 text-gray-500">
          {{ item.content }}
        </p>
        <!-- 回复按钮 -->
        <div>
          <el-popover placement="bottom-start" trigger="click" :width="200">
            <template #reference>
              <el-button style="float: right" link type="primary"
                >回复</el-button
              >
            </template>
            <el-input v-model="input" />
            <el-button @click="submit(item.userId, item.id)" style="width: 100%"
              >确定</el-button
            >
          </el-popover>
        </div>
      </div>
      <!-- 回复 -->
      <div v-for="subItem in item.children" :key="subItem.id">
        <div
          class="relative gap-4 p-6 rounded-lg mb-8 bg-white border"
          style="margin-left: 50px"
        >
          <div class="relative flex gap-4">
            <img
              src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
              class="relative rounded-lg -top-8 -mb-4 bg-white border h-20 w-20"
              alt=""
              loading="lazy"
            />

            <div class="flex flex-col w-full">
              <div class="flex flex-row justify-between">
                <p
                  class="relative text-xl whitespace-nowrap truncate overflow-hidden"
                >
                  {{ getUser(subItem.userId) }} 回复
                  <span style="color: cornflowerblue"
                    >@{{ getUser(subItem.replyUserId) }}</span
                  >
                </p>
                <a class="text-gray-500 text-xl" href="#"
                  ><i class="fa-solid fa-trash"
                /></a>
              </div>
              <p class="text-gray-400 text-sm">{{ item.createTime }}</p>
            </div>
          </div>
          <p class="-mt-4 text-gray-500">
            {{ subItem.content }}
          </p>
          <!-- 回复按钮 -->
          <div>
            <el-popover placement="bottom-start" trigger="click" :width="200">
              <template #reference>
                <el-button style="float: right" link type="primary"
                  >回复</el-button
                >
              </template>
              <el-input v-model="input" />
              <el-button
                @click="submit(item.userId, item.id)"
                style="width: 100%"
                >确定</el-button
              >
            </el-popover>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
