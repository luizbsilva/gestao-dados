import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NaoAutorizadoComponent } from './core/nao-autorizado.component';
import { PaginaNaoEncontradaComponent } from './core/pagina-nao-encontrada.component';

const routes: Routes = [
  { path: 'pessoas', loadChildren: './pessoas/pessoas.module#PessoasModule' },
  { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
  { path: 'usuarios', loadChildren: './usuarios/usuarios.module#UsuariosModule' },
  { path: 'permissoes', loadChildren: './permissoes/permissoes.module#PermissoesModule' },
  { path: 'nao-autorizado', component: NaoAutorizadoComponent },
  { path: 'pagina-nao-encontrada', component: PaginaNaoEncontradaComponent },
  { path: '**', redirectTo: 'pagina-nao-encontrada' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
