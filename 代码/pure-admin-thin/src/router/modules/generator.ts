const { VITE_HIDE_HOME } = import.meta.env;
const Layout = () => import("@/layout/index.vue");

export default {
  path: "/gen",
  name: "generator",
  component: Layout,
  redirect: "/gen",
  meta: {
    icon: "homeFilled",
    title: "首页",
    rank: 0
  },
  children: [
    {
      path: "/gen",
      name: "generator",
      component: () => import("@/views/generator/course.vue"),
      meta: {
        title: "生成代码",
        showLink: VITE_HIDE_HOME === "true" ? false : true
      }
    }
  ]
} as RouteConfigsTable;
