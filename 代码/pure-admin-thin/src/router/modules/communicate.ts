const { VITE_HIDE_HOME } = import.meta.env;
const Layout = () => import("@/layout/index.vue");

export default {
  path: "/communicate",
  name: "communicate",
  component: Layout,
  redirect: "/communicate",
  meta: {
    icon: "homeFilled",
    title: "沟通",
    rank: 0
  },
  children: [
    {
      path: "/communicate",
      name: "communicate",
      component: () => import("@/views/communicate/communicate.vue"),
      meta: {
        title: "评论",
        showLink: VITE_HIDE_HOME === "true" ? false : true
      }
    },
    {
      path: "/communicate2",
      name: "communicate2",
      component: () => import("@/views/communicate/communicate2.vue"),
      meta: {
        title: "评论2",
        showLink: VITE_HIDE_HOME === "true" ? false : true
      }
    }
  ]
} as RouteConfigsTable;
