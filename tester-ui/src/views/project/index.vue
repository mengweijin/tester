<template>
  <div>
    <el-row>
        <el-button @click="handleAddClick()" type="primary" plain  icon="el-icon-plus">添加项目</el-button>
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
              <el-form-item label="项目名称"><span>{{ props.row.name }}</span></el-form-item>
              <el-form-item label="描述"><span>{{ props.row.description }}</span></el-form-item>
              <el-form-item label="数据源名称"><span>{{ props.row.dataSourceName }}</span></el-form-item>
              <el-form-item label="创建时间"><span>{{ props.row.createTime }}</span></el-form-item>
              <el-form-item label="最后修改时间"><span>{{ props.row.updateTime }}</span></el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="ID" min-width="180" sortable></el-table-column>
        <el-table-column prop="name" label="项目名称" min-width="300"></el-table-column>
        <el-table-column prop="description" label="描述" min-width="500"></el-table-column>
        <el-table-column prop="dataSourceName" label="数据源名称" min-width="200"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column prop="updateTime" label="最后修改时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column fixed="right" label="操作" width="130">
            <template slot-scope="scope">
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
        :page-sizes="[10, 30]"
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
        this.$get('/system/test/project', {current: current, size: size})
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
      handleDeleteClick(index, row) {
        let _this = this
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.$delete('/system/test/project/' + row.id)
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