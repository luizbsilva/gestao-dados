import { Title } from '@angular/platform-browser';
import { Component, OnInit, ViewChild } from '@angular/core';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { DashboardService, Filtro } from '../dashboard.service';

@Component({
  selector: 'app-dashboard-por-tipo-media-idade',
  templateUrl: './dashboard-por-tipo-media-idade.component.html',
  styleUrls: ['./dashboard-por-tipo-media-idade.component.css']
})
export class DashboardPorTipoSanguineoMediaIdadeComponent implements OnInit {

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

  pesquisar() {
    
    this.dashboardService.porTipoSanguineoEMedia()
      .then(resultado => {
        this.doadores = resultado;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  

}
