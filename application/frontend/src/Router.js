/* @flow */
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
      path: "/newPerson",
      name: "NewPerson",
      props: false,
      component: () => import(/* webpackChuckName: "newUser" */ "./components/NewPerson")
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