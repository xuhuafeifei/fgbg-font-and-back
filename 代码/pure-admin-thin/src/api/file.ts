import { http } from "@/utils/http";
import { R, baseUrlApi } from "./utils";

/** upload batch */
export const uploadBatch = (data: FormData) => {
  return http.request<R<any>>("post", baseUrlApi("common/file/uploadList"), {
    data,
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });
};

/** upload */
export const upload = (data: FormData) => {
  return http.request<R<any>>("post", baseUrlApi("common/file/upload"), {
    data,
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });
};
