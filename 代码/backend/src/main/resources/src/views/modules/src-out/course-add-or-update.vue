<template>
  <el-dialog
    :title="!dataForm.courseId ? '新增' : '修改'"
    :close-on-click-modal="false"
    v-model:visible="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter="dataFormSubmit()"
      label-width="80px"
    >
      <el-form-item label="" prop="courseName">
        <el-input v-model:value="dataForm.courseName" placeholder=""></el-input>
      </el-form-item>
    </el-form>
    <template v-slot:footer>
      <span class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { $on, $off, $once, $emit } from 'utils/gogocodeTransfer'
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        courseId: 0,
        courseName: '',
      },
      dataRule: {
        courseName: [{ required: true, message: '不能为空', trigger: 'blur' }],
      },
    }
  },
  methods: {
    init(id) {
      this.dataForm.courseId = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.courseId) {
          this.$http({
            url: this.$http.adornUrl(
              `/demo/course/info/${this.dataForm.courseId}`
            ),
            method: 'get',
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.courseName = data.course.courseName
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/demo/course/${!this.dataForm.courseId ? 'save' : 'update'}`
            ),
            method: 'post',
            data: this.$http.adornData({
              courseId: this.dataForm.courseId || undefined,
              courseName: this.dataForm.courseName,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  $emit(this, 'refreshDataList')
                },
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
  },
  emits: ['refreshDataList'],
}
</script>
