import request from '@/utils/request'

// 查询菜单列表
export function listMenu(data) {
  return request({
    url: '/system/customers/list',
    method: 'get',
    params: data
  })
}