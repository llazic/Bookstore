import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/book/:id',
    name: 'BookDetailedView',
    component: () => import('../views/Buyer/BookDetailedView.vue')
  },
  {
    path: '/buyer',
    name: 'Landing',
    component: () => import('../views/Buyer/Landing.vue')
  },
  {
    path: '/buyer/recommendations',
    name: 'RecommendedBooks',
    component: () => import('@/views/Buyer/RecommendedBooks.vue')
  },
  {
    path: '/buyer/profile',
    name: 'ProfileChange',
    component: () => import('@/views/Buyer/ProfileChange.vue')
  },
  {
    path: '/seller',
    name: 'Landing',
    component: () => import('@/views/Seller/Landing.vue')
  },
  {
    path: '/seller/profile',
    name: 'ProfileChange',
    component: () => import('@/views/Seller/ProfileChange.vue')
  },
  {
    path: '/seller/add',
    name: 'AddBook',
    component: () => import('@/views/Seller/AddBook.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
