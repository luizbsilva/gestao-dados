import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../seguranca/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardPorEstadosComponent } from './dashboard-por-estados/dashboard-por-estados.component';
import { DashboardPorImcComponent } from './dashboard-por-imc/dashboard-por-imc.component';
import { DashboardPorPercentualObesosComponent } from './dashboard-por-obesos/dashboard-por-obesos.component';
import { DashboardPorTipoSanguineoMediaIdadeComponent } from './dashboard-por-tipo-media-idade/dashboard-por-tipo-media-idade.component';
import { DashboardPorQuantidadePorTipoSanguineoComponent } from './dashboard-quantidade-por-tipo-sanguineo/dashboard-quantidade-por-tipo-sanguineo.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    canActivate: [ AuthGuard ],
    data: { roles: ['ROLE_PESQUISAR_DASHBOARD'] }
  },
  {
    path: 'por-estados',
    component: DashboardPorEstadosComponent,
    canActivate: [ AuthGuard ],
    data: { roles: ['ROLE_PESQUISAR_DASHBOARD'] }
  },
  {
    path: 'por-imc',
    component: DashboardPorImcComponent,
    canActivate: [ AuthGuard ],
    data: { roles: ['ROLE_PESQUISAR_DASHBOARD'] }
  },
  {
    path: 'percentual-obesos',
    component: DashboardPorPercentualObesosComponent,
    canActivate: [ AuthGuard ],
    data: { roles: ['ROLE_PESQUISAR_DASHBOARD'] }
  },
  {
    path: 'tipo-sanguineo-por-media-idade',
    component: DashboardPorTipoSanguineoMediaIdadeComponent,
    canActivate: [ AuthGuard ],
    data: { roles: ['ROLE_PESQUISAR_DASHBOARD'] }
  },
  {
    path: 'quantidade-por-tipo-sanguineo',
    component: DashboardPorQuantidadePorTipoSanguineoComponent,
    canActivate: [ AuthGuard ],
    data: { roles: ['ROLE_PESQUISAR_DASHBOARD'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
