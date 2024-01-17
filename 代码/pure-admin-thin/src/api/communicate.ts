import { http } from "@/utils/http";
import { R, baseUrlApi } from "./utils";

export class CommunicateEntity {
  id: Number;
  content: String;
  pid: Number;
  createTime: String;
  replyUserId: Number;
  children: Array<CommunicateEntity>;
}

/** list */
export const list = () => {
  return http.request<R<Array<CommunicateEntity>>>(
    "get",
    baseUrlApi("comm/list")
  );
};

/** save */
export const save = (data?: CommunicateEntity) => {
  return http.request<R<any>>("post", baseUrlApi("comm/save"), {
    data
  });
};
