import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Mascota } from '../models/mascota.model';



@Injectable({
  providedIn: 'root'
})
export class MascotaService {

  private baseURL = 'http://localhost:8065/api/mascotas';

  constructor(private http: HttpClient) { }

  listarMascotas(): Observable<Mascota[]>{
    return this.http.get<{ mascota: Mascota[] }>(this.baseURL).pipe(
    map(response => response.mascota)
    );
  }

  registrarMascota(mascota: Mascota): Observable<Mascota>{
    return this.http.post<Mascota>(this.baseURL, mascota);
  }

  eliminarMascota(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }

  obtenerMascota(id: number): Observable<Mascota>{
    return this.http.get<Mascota>(`${this.baseURL}/${id}`);
  }

  actualizarMascota(id: number, mascota: Mascota):Observable<Mascota>{
    return this.http.put<Mascota>(`${this.baseURL}/${id}`,mascota);
  }
}
