import { Routes } from '@angular/router';
import { ListaMascotasComponent } from './components/lista-mascotas/lista-mascotas.component';

export const routes: Routes = [
    { path: '', redirectTo: 'mascotas', pathMatch: 'full'},
    { path: 'mascotas', component: ListaMascotasComponent}
];
