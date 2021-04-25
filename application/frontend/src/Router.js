import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Login",
      component: () => import(/* webpackChuckName: "newUser" */ "./components/Login")
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
      props: true,
      component: () => import(/* webpackChuckName: "dashboard" */ "./components/Dashboard")
    },
    {
      path: "/profile",
      name: "Profile",
      props: true,
      component: () => import(/* webpackChuckName: "dashboard" */ "./components/Profile")
    },
    {
      path: "/marketplace",
      name: "Marketplace",
      props: true,
      component: () => import(/* webpackChuckName: "dashboard" */ "./components/Marketplace")
    }
  ]
});