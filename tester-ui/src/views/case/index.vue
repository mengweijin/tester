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
            <el-form label-position="left" class="table-expand">
              <el-form-item label="ID"><span>{{ props.row.id }}</span></el-form-item>
              <el-form-item label="API URL"><span>{{ props.row.apiUrl }}</span></el-form-item>
              <el-form-item label="测试用例名称"><span>{{ props.row.name }}</span></el-form-item>
              <el-form-item label="描述"><span>{{ props.row.description }}</span></el-form-item>
              <el-form-item label="准备数据的SQL"><span>{{ props.row.preparedDataSql }}</span></el-form-item>
              <el-form-item label="请求URL"><span>{{ props.row.requestUrl }}</span></el-form-item>             
              <el-form-item label="URL参数"><br><span>{{ props.row.urlParams }}</span></el-form-item>
              <el-form-item label="请求方式"><span>{{ props.row.httpMethod }}</span></el-form-item>
              <el-form-item label="请求参数"><br><span>{{ props.row.requestParams }}</span></el-form-item>
              <el-form-item label="状态"><span>{{ props.row.status }}</span></el-form-item>
              <el-form-item label="Feature"><br><span>{{ props.row.feature }}</span></el-form-item>
              <el-form-item label="失败信息"><span>{{ props.row.failedMessage }}</span></el-form-item>
              <el-form-item label="创建时间"><span>{{ $dayjs(props.row.createTime).format("YYYY-MM-DD HH:mm:ss") }}</span></el-form-item>
              <el-form-item label="最后修改时间"><span>{{ $dayjs(props.row.updateTime).format("YYYY-MM-DD HH:mm:ss") }}</span></el-form-item>
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
                <el-button @click="handleEditClick(scope.row)" type="text" size="medium" icon="el-icon-edit-outline" title="编辑"></el-button>
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

    <!-- 添加/编辑对话框 -->
    <el-dialog title="新增/编辑" :visible.sync="open" width="60%">
      <el-form ref="form" :model="form" :rules="rules" label-width="10%">
        <el-form-item label="ID" prop="id" v-show="true">
          <el-input v-model="form.id" :disabled="true"/>
        </el-form-item>
        <el-form-item label="API接口" prop="apiId">
          <el-select filterable remote :remote-method="remoteMethod" :loading="loading" v-model="form.apiId" clearable placeholder="输入关键词进行搜索" style="width:100%">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.url"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用例名称" prop="name">
          <el-input v-model="form.name" maxlength="100" show-word-limit/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" maxlength="500" show-word-limit/>
        </el-form-item>
        <el-form-item label="准备数据的SQL" prop="preparedDataSql">
          <el-input type="textarea" autosize v-model="form.preparedDataSql" maxlength="30000" show-word-limit/>
        </el-form-item>
        <el-form-item label="请求URL" prop="requestUrl">
          <el-input v-model="form.requestUrl" maxlength="500" show-word-limit/>
        </el-form-item>
        <el-form-item label="URL参数" prop="urlParams">
          <el-input type="textarea" autosize v-model="form.urlParams" maxlength="30000" show-word-limit/>
        </el-form-item>
        <el-form-item label="请求方式" prop="httpMethod">
          <el-select v-model="form.httpMethod" clearable placeholder="请选择" style="width:100%">
            <el-option key="GET" label="GET" value="GET"></el-option>
            <el-option key="HEAD" label="HEAD" value="HEAD"></el-option>
            <el-option key="POST" label="POST" value="POST"></el-option>
            <el-option key="PUT" label="PUT" value="PUT"></el-option>
            <el-option key="PATCH" label="PATCH" value="PATCH"></el-option>
            <el-option key="DELETE" label="DELETE" value="DELETE"></el-option>
            <el-option key="OPTIONS" label="OPTIONS" value="OPTIONS"></el-option>
            <el-option key="TRACE" label="TRACE" value="TRACE"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="请求参数" prop="requestParams">
          <el-input type="textarea" autosize v-model="form.requestParams" maxlength="30000" show-word-limit/>
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
            apiId: [
                {required: true, message: "不能为空", trigger: "blur"}
            ],
            name: [
                {required: true, message: "不能为空", trigger: "blur"}
            ],
            description: [
                {required: true, message: "不能为空", trigger: "blur"}
            ],
            preparedDataSql: [
                {required: false, message: "不能为空", trigger: "blur"}
            ],
            requestUrl: [
                {required: true, message: "不能为空", trigger: "blur"}
            ],
            urlParams: [
                {required: false, message: "不能为空", trigger: "blur"}
            ],
            httpMethod: [
                {required: true, message: "不能为空", trigger: "blur"}
            ],
            requestParams: [
                {required: false, message: "不能为空", trigger: "blur"}
            ]
        },
        options: []
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
      handleEditClick(row) {
        let _this = this;
        this.$get('/system/test/api/' + row.apiId)
          .then(function (response) {
            _this.options.push(response)
            _this.form = row
            _this.open = true
          })
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
      },
      handleAddClick() {
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
                  this.$post('/system/test/case', this.form)
                  .then(function (response) {
                      _this.$message({ message: '操作成功！', type: 'success'})
                      _this.open = false
                      _this.loadTableData(1, _this.pageSize)
                      this.form = {}
                  })
                } else {
                  this.$put('/system/test/case', this.form)
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
      remoteMethod(query) {
        if (query !== '') {
          this.loading = true
          let _this = this;
          this.$get('/system/test/api/url/' + query)
          .then(function (response) {
            _this.options = response
            _this.loading = false
          })
        } else {
          this.options = []
        }
      }
    },
    created: function() {
      this.loadTableData(this.currentPage, this.pageSize);
    }
  }
</script>

<style>

</style>