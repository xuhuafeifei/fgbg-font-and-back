import { http } from "@/utils/http";
import { R, baseUrlApi } from "./utils";

export class UnitEntity {
  id: Number;
  unit: String;
  pid: Number;
  children: Array<UnitEntity>;
}

export class LabelVo {
  id: Number;
  label: String;
  value: String;
  children: Array<LabelVo>;
}

/** 获取全部的unit数据 */
export const getData = () => {
  return http.request<R<Array<UnitEntity>>>("get", baseUrlApi("unit/list"));
};

/** 获取全部的treeLabel */
export const getLabelTree = () => {
  return http.request<R<Array<LabelVo>>>(
    "get",
    baseUrlApi("unit/listTreeSelect")
  );
};

/** 根据id查询节点 */
export const getNodeById = (id: Number) => {
  return http.request<R<LabelVo>>("get", baseUrlApi(`unit/listNode?id=${id}`));
};

/** save or update */
export const saveOrUpdate = (data?: UnitEntity) => {
  return http.request<R<any>>("post", baseUrlApi("unit/saveOrUpdate"), {
    data
  });
};
