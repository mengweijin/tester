import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout/index'
import AsideLayout from '@/layout/asideLayout'
import MengweijinHeader from '@/layout/mengweijinHeader'
import Header from '@/layout/header'
import Footer from '@/layout/footer'
import ProjectTree from '@/views/project/projectTree'

/**
 * 重写路由的push方法
 */
const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return routerPush.call(this, location).catch(error => error)
}

Vue.use(Router)

// 公共路由
export const Routers = [{
        path: '',
        component: Layout,
        redirect: '/index',
        children: [{
            path: '/index',
            components: {
                mengweijinHeader: MengweijinHeader,
                header: Header,
                default: () =>
                    import ('@/views/index'),
                footer: Footer
            }
        }]
    },
    {
        path: '/test/case',
        component: AsideLayout,
        redirect: '/test/case/index',
        children: [{
            path: '/test/case/index',
            components: {
                mengweijinHeader: MengweijinHeader,
                header: Header,
                projectTree: ProjectTree,
                default: () =>
                    import ('@/views/index'),
                footer: Footer
            }
        }]
    },
    {
        path: '/404',
        component: () =>
            import ('@/views/error/404'),
    }

]

export default new Router({
    // 去掉url中的#
    mode: 'history',
    scrollBehavior: () => ({ y: 0 }),
    routes: Routers
})