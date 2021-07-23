<template>
  <div class="app-container">
    <el-table :data="roleList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机构编号" prop="deptId" width="120" />
      <el-table-column label="角色名称" prop="nickname" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="用户账号" prop="username" width="100" />
      <el-table-column label="用户手机号" align="center" width="180" prop="phone"></el-table-column>
      <el-table-column label="用户ID" align="center" width="180" prop="id"></el-table-column>
      <el-table-column label="用户签名" align="center" prop="doodling" width="300">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getUserProfile } from "@/api/system/user";
import { listMenu} from "@/api/system/account";
export default {
  name: "account",
  data() {
    return {
      // 角色表格数据
      roleList: [],
      dataList: [],
      deptId: '',
      loading: true,
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.roleId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    getUser() {
      getUserProfile().then(res => {
        console.log(res)
        this.deptId = res.data.deptId
        let data = {
          deptId:this.deptId
        }
        listMenu(data).then( res =>{
        console.log(res.data)
        this.roleList = res.data
      })
      })
    }
  }
}
</script>
