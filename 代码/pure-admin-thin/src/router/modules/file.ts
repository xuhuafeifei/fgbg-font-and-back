const { VITE_HIDE_HOME } = import.meta.env;
const Layout = () => import("@/layout/index.vue");

export default {
  path: "/file",
  name: "file",
  component: Layout,
  redirect: "/pict",
  meta: {
    icon: "homeFilled",
    title: "文件",
    rank: 0
  },
  children: [
    {
      path: "/pict",
      name: "pict",
      component: () => import("@/views/file/pict.vue"),
      meta: {
        title: "图片",
        showLink: VITE_HIDE_HOME === "true" ? false : true
      }
    },
    {
      path: "/father",
      name: "father",
      component: () => import("@/views/file/father.vue"),
      meta: {
        title: "图片父组件",
        showLink: VITE_HIDE_HOME === "true" ? false : true
      }
    },
    {
      path: "/testPict",
      name: "testPict",
      component: () => import("@/views/file/pict2.vue"),
      meta: {
        title: "图片2",
        showLink: VITE_HIDE_HOME === "true" ? false : true
      }
    }
  ]
} as RouteConfigsTable;
