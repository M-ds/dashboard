import Vue from "vue";
import Router from "vue-router";
import Login from "@/components/Login"

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Login",
      component: Login
    },
    {
      path: "/newUser",
      name: "NewUser",
      props: false,
      component: () => import(/* webpackChuckName: "newUser" */ "./components/NewUser")
    },
    {
      path: "/dashboard",
      name: "Dashboard",
      props: false,
      component: () => import(/* webpackChuckName: "dashboard" */ "./components/Dashboard")
    }
  ]
})