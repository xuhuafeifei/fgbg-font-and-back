export const baseUrlApi = (url: string) => `/api_demo/${url}`;

/** 后端返回通用数据类型 */
export type R<T> = {
  code: Number;
  msg: String;
  data: T;
};

/** 同步休眠函数, 参数为毫秒 */
export const sleep = (ms: number): Promise<void> => {
  return new Promise(resolve => setTimeout(resolve, ms));
};

/** 分页数据类型 */
export type PageUtils<T> = {
  /** 总记录数 */
  totalCount: number;
  /** 每页记录数 */
  pageSize: number;
  /** 总页数 */
  totalPage: number;
  /** 当前页数 */
  currPage: number;
  /** 列表数据 */
  list: Array<T>;
};

export const getStoreUser = () => {
  const res = sessionStorage.getItem("user-info");
  // const res = sessionStorage.getItem("user-info");
  console.log(res);
  return JSON.parse(res);
};
