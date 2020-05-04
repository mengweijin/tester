<template>
  <div>
    <el-row>
        <el-button @click="handleAddClick()" type="primary" plain  icon="el-icon-plus">添加测试用例</el-button>
    </el-row>
    <el-table 
        v-loading="loading" 
        element-loading-text="拼命加载中" 
        element-loading-spinner="el-icon-loading" 
        element-loading-background="rgba(0, 0, 0, 0.5)" 
        :data="tableData" 
        :row-style="{height:'40px'}" 
        :cell-style="{padding:'5px 0'}">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left">
              <el-form-item label="ID"><span>{{ props.row.id }}</span></el-form-item>
              <el-form-item label="API URL"><span>{{ props.row.apiUrl }}</span></el-form-item>
              <el-form-item label="测试用例名称"><span>{{ props.row.name }}</span></el-form-item>
              <el-form-item label="描述"><span>{{ props.row.description }}</span></el-form-item>
              <el-form-item label="准备数据的SQL"><span>{{ props.row.preparedDataSql }}</span></el-form-item>
              <el-form-item label="请求URL"><span>{{ props.row.requestUrl }}</span></el-form-item>             
              <el-form-item label="URL参数"><span>{{ props.row.urlParams }}</span></el-form-item>
              <el-form-item label="请求方式"><span>{{ props.row.httpMethod }}</span></el-form-item>
              <el-form-item label="请求参数"><span>{{ props.row.requestParams }}</span></el-form-item>
              <el-form-item label="状态"><span>{{ props.row.status }}</span></el-form-item>
              <el-form-item label="Feature"><span>{{ props.row.feature }}</span></el-form-item>
              <el-form-item label="失败信息"><span>{{ props.row.failedMessage }}</span></el-form-item>
              <el-form-item label="创建时间"><span>{{ props.row.createTime }}</span></el-form-item>
              <el-form-item label="最后修改时间"><span>{{ props.row.updateTime }}</span></el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="测试用例ID" min-width="180" sortable v-if="true"></el-table-column>
        <el-table-column prop="apiUrl" label="API URL" min-width="200" sortable></el-table-column>
        <el-table-column prop="name" label="用例名称" min-width="180"></el-table-column>
        <el-table-column prop="requestUrl" label="请求URL" min-width="300"></el-table-column>
        <el-table-column prop="urlParams" label="URL参数" min-width="200"></el-table-column>
        <el-table-column prop="httpMethod" label="请求方式" min-width="100"></el-table-column>
        <el-table-column prop="requestParams" label="请求参数" min-width="200"></el-table-column>
        <el-table-column prop="status" label="状态" min-width="120">
            <template slot-scope="scope">
                <el-tag v-if="scope.row.status==='WAITING'" type="warning" size="medium" effect="dark">{{ scope.row.status }}</el-tag>
                <el-tag v-if="scope.row.status==='RUNNING'" type="info" size="medium" effect="dark">{{ scope.row.status }}</el-tag>
                <el-tag v-if="scope.row.status==='SUCCESS'" type="success" size="medium" effect="dark">{{ scope.row.status }}</el-tag>
                <el-tag v-if="scope.row.status==='FAILED'" type="danger" size="medium" effect="dark">{{ scope.row.status }}</el-tag>
            </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column prop="updateTime" label="最后修改时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column fixed="right" label="操作" width="130">
            <template slot-scope="scope">
                <el-button @click="handleDetailClick(scope.row)" type="text" size="medium" title="测试用例详情">
                  <svg class="icon" aria-hidden="true"><use xlink:href="#icondetail"></use></svg>
                </el-button>
                <el-button @click="handleRunCaseClick(scope.row)" type="text" size="medium" icon="el-icon-video-play" title="执行测试用例"></el-button>
                <el-button @click="handleDeleteClick(scope.$index, scope.row)" type="text" size="medium" icon="el-icon-delete" style="color:red" title="删除">
                </el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 30, 50, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount">
    </el-pagination>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data: function() {   
      return {
        loading: true,
        tableData: [],
        currentPage: 1,
        totalCount: 0,
        pageSize: 10,
      } 
    },
    methods:{
      loadTableData(current, size) {
        let _this = this;
        this.$get('/system/test/case', {current: current, size: size})
        .then(function (response) {
          _this.tableData = response.dataList
          _this.totalCount = response.total
          _this.loading = false
        })
      },
      handleSizeChange(val) {
        this.currentPage = 1
        this.pageSize = val
        this.loadTableData(this.currentPage, this.pageSize)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.loadTableData(this.currentPage, this.pageSize)
      },
      handleDetailClick(row) {

      },
      handleRunCaseClick(row) {
        let _this = this;
        this.$get('/system/test/case/run/' + row.id)
        .then(function (response) {
          setTimeout(function(){
            _this.loadTableData(_this.currentPage, _this.pageSize)
          }, 2000);
        })
      },
      handleDeleteClick(index, row) {
        let _this = this
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.$delete('/system/test/case/' + row.id)
            .then(function (res) {
              _this.tableData.splice(index, 1)
              _this.totalCount--
              _this.$message({ message: '操作成功！', type: 'success'});
            })
        })
      },
      dateTimeFormat(row, column) {
        let date = row[column.property]
        if(date == undefined){return ''}
        return this.$dayjs(date).format("YYYY-MM-DD HH:mm:ss")
      }
    },
    created: function() {
      this.loadTableData(this.currentPage, this.pageSize);
    }
  }
</script>

<style>

</style>