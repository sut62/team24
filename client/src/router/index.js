import Vue from 'vue'
import VueRouter from 'vue-router'
import FlightBookingUI from '../views/FlightBookingUI'
import CheckIn from '../views/CheckIn.vue'
import Flight from '../views/Flight.vue'
import AddsOn from "../views/AddsOn.vue";
import Payment from '../views/Payment.vue'
import Promotion from '../views/Promotion.vue'
import VeriflyCheckIn from '../views/VerifyCheckIn.vue'
import CheckPayment from '../views/CheckPayment.vue'
import CheckTicket from '../views/CheckTicket.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/book-flight'
  },
  {
    path: '/book-flight',
    name: 'bookFlight',
    component: FlightBookingUI
  },
  {
    path: '/payment/:bookId',
    name: 'payment',
    component: Payment,
    props: true
  },
  {
    path: '/checkIn',
    name: 'checkIn',
    component: CheckIn
  },
  {
    path: '/flight',
    name: 'flight',
    component: Flight
  },
  {
    path: '/adds-on',
    name: 'addsOn',
    component: AddsOn
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/promotion',
    name: 'promotion',
    component: Promotion
  },

  {
    path: '/check-payment',
    name: 'checkPayment',
    component: CheckPayment
  },
  
  {
    path: '/check-payment/:mode',
    name: 'checkPaymentTest',
    component: CheckPayment,
    props: true
  },

  {
    path: '*',
    redirect: '/book-flight'
  },
  {
    path: '/verify-checkIn',
    name: 'VerifyCheckIn',
    component: VeriflyCheckIn
  },
  {
    path: '/verify-checkIn/:mode',
    name: 'VerifyCheckInTest',
    component: VeriflyCheckIn,
    props: true
  },
  {
    path: '/check-ticket',
    name: 'CheckTicket',
    component: CheckTicket
  },
  {
    path: '/check-ticket/:mode',
    name: 'CheckTicket',
    component: CheckTicket,
    props: true
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
