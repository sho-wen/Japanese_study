import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApiResponse } from '../model/api.response';
import { RoleVo } from '../model/vo/role-vo';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  getRoleListAll(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.baseUrl}/role/lists`);
  }

  // getRoleListPage(): Observable<ApiResponse> {
  //   return this.http.get<ApiResponse>(`${environment.baseUrl}/role/list`);
  // }

  getRoleInfo(roleId: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.baseUrl}/role/info/${roleId}`);
  }

  addRole(roleVo: RoleVo): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${environment.baseUrl}/role/save`, roleVo);
  }

  roleUpdateById(roleVo: RoleVo): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${environment.baseUrl}/role/update`, roleVo);
  }

  roleAddPermById(roleId: number, menuIds: number[]): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${environment.baseUrl}/role/perm/${roleId}`, menuIds);
  }

  deleteRoleByIds(roleIds: number[]): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${environment.baseUrl}/role/delete`, roleIds);
  }

  searchRoles(message: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.baseUrl}/role/list/${message}`);
  }
}
