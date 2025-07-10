import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Mascota } from '../../models/mascota.model';
import { MascotaService } from '../../services/mascota.service';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-lista-mascotas',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule],
  templateUrl: './lista-mascotas.component.html',
  styleUrls: ['./lista-mascotas.component.css']
})
export class ListaMascotasComponent implements OnInit {
  mascotas: Mascota[] = [];
  displayedColumns: string[] = ['id', 'nombre', 'edad', 'raza', 'sexo', 'tamanio', 'tipo', 'acciones'];

  constructor(private mascotaService: MascotaService) {}

  ngOnInit(): void {
    this.mascotaService.listarMascotas().subscribe({
      next: (data) => {
        console.log('✅ Mascotas cargadas:', data);
        this.mascotas = data;
      },
      error: (err) => console.error('❌ Error al obtener mascotas', err)
    });
    
  }
  editarMascota(mascota: Mascota) {
  console.log('Editar mascota:', mascota);
  // aquí iría la navegación o apertura de formulario
}

eliminarMascota(id: number) {
  console.log('Eliminar mascota con ID:', id);
  // aquí iría la lógica para eliminar
}

}

