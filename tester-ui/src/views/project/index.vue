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
            <el-form label-position="left" class="table-expand">
              <el-form-item label="ID"><span>{{ props.row.id }}</span></el-form-item>
              <el-form-item label="项目名称"><span>{{ props.row.name }}</span></el-form-item>
              <el-form-item label="描述"><span>{{ props.row.description }}</span></el-form-item>
              <el-form-item label="数据源名称"><span>{{ props.row.dataSourceName }}</span></el-form-item>
              <el-form-item label="创建时间"><span>{{ $dayjs(props.row.createTime).format("YYYY-MM-DD HH:mm:ss") }}</span></el-form-item>
              <el-form-item label="最后修改时间"><span>{{ $dayjs(props.row.updateTime).format("YYYY-MM-DD HH:mm:ss") }}</span></el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="ID" min-width="180" sortable v-if="true"></el-table-column>
        <el-table-column prop="name" label="项目名称" min-width="300"></el-table-column>
        <el-table-column prop="description" label="描述" min-width="500"></el-table-column>
        <el-table-column prop="dataSourceName" label="数据源名称" min-width="200"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column prop="updateTime" label="最后修改时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column fixed="right" label="操作" width="130">
            <template slot-scope="scope">
                <el-button @click="handleEditClick(scope.row)" type="text" size="medium" icon="el-icon-edit-outline" title="编辑"></el-button>
                <el-button @click="handleDeleteClick(scope.$index, scope.row)" type="text" size="medium" icon="el-icon-delete" style="color:red" title="删除"></el-button>
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
    
    <!-- 添加/编辑对话框 -->
    <el-dialog title="新增/编辑" :visible.sync="open" width="60%">
      <el-form ref="form" :model="form" :rules="rules" label-width="10%">
        <el-form-item label="ID" prop="id" v-show="true">
          <el-input v-model="form.id" :disabled="true"/>
        </el-form-item>
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入项目名称" maxlength="100" show-word-limit/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" autosize v-model="form.description" maxlength="500" show-word-limit />
        </el-form-item>
        <el-form-item label="数据源" prop="dataSourceId">
          <el-select v-model="form.dataSourceId" clearable placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <!-- 
              native-type="button"
              在Internet Explorer 表单提交的默认类型是 “button”，而其他浏览器中（包括 W3C 规范）的默认值是 “submit”
              否则：java.io.IOException: 你的主机中的软件中止了一个已建立的连接
           -->
        <el-button type="primary" native-type="button" @click="submitForm('form')">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

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
        // 是否显示弹层
        open: false,
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            name: [
                {required: true, message: "不能为空", trigger: "blur"}
            ],
            dataSourceId: [
                {required: true, message: "不能为空", trigger: "blur"}
            ]
        },
        options: []
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
      handleEditClick(row) {
        this.loadDataSourceListData()
        this.form = row
        this.open = true
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
      },
      handleAddClick() {
        this.loadDataSourceListData()
        this.form = {}
        this.open = true
      },
      cancel() {
        this.open = false
      },
      submitForm(formName) {
          let _this = this
          this.$refs[formName].validate(valid => {
              if(valid) {
                _this.form.createTime = undefined
                _this.form.updateTime = undefined
                if(_this.form.id === undefined) {
                  this.$post('/system/test/project', this.form)
                  .then(function (response) {
                      _this.$message({ message: '操作成功！', type: 'success'})
                      _this.open = false
                      _this.loadTableData(1, _this.pageSize)
                      this.form = {}
                  })
                } else {
                  this.$put('/system/test/project', this.form)
                  .then(function (response) {
                      _this.$message({ message: '操作成功！', type: 'success'})
                      _this.open = false
                      _this.loadTableData(1, _this.pageSize)
                      this.form = {}
                  })
                }
                  
              }
          })
      },
      loadDataSourceListData() {
        let _this = this;
        this.$get('/system/test/dataSource/list')
        .then(function (response) {
          _this.options = response
        })
      }
    },
    created: function() {
      this.loadTableData(this.currentPage, this.pageSize);
    }
  }
</script>

<style>

</style>