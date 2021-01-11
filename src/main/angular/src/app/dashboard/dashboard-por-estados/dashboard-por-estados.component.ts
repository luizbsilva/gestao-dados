import { Title } from '@angular/platform-browser';
import { Component, OnInit, ViewChild } from '@angular/core';

import { LazyLoadEvent, ConfirmationService } from 'primeng/components/common/api';
import { MessageService } from 'primeng/components/common/messageservice';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { DashboardService, Filtro } from '../dashboard.service';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './dashboard-por-estados.component.html',
  styleUrls: ['./dashboard-por-estados.component.css']
})
export class DashboardPorEstadosComponent implements OnInit {

  totalRegistros = 0;
  filtro = new Filtro();
  doadores = [];
  @ViewChild('tabela') grid;

  constructor(
    private dashboardService: DashboardService,
    private errorHandler: ErrorHandlerService,
    private title: Title
  ) { }

  ngOnInit() {
    this.title.setTitle('Pesquisa de Doadores por Estados');
    this.pesquisar();
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.dashboardService.dadosPorEstado(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.doadores = resultado.doaores;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  

}
