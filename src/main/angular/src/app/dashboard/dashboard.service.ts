import { Injectable } from '@angular/core';

import 'rxjs/operator/toPromise';
import * as moment from 'moment';

import { environment } from './../../environments/environment';
import { MoneyHttp } from '../seguranca/money-http';
import { HttpParams } from '@angular/common/http';

export class Filtro {
  pagina = 0;
  itensPorPagina = 100;
}

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  dashboardUrl: string;

  constructor(private http: MoneyHttp) {
    this.dashboardUrl = `${environment.apiUrl}/dashboard`;
  }
  
  dadosPorEstado(filtro: Filtro): Promise<any> {
    let params = new HttpParams({
      fromObject: {
        page: filtro.pagina.toString(),
        size: filtro.itensPorPagina.toString()
      }
    });

    return this.http.get<any>(`${this.dashboardUrl}/estatisticas/por-estado`, { params })
      .toPromise()
      .then(response => {
        const doaores = response.content;
        const resultado = {
          doaores,
          total: response.totalElements
        };

        return resultado;
      });
  }
  
  porImc(): Promise<any> {
    return this.http.get<any>(`${this.dashboardUrl}/estatisticas/imc-por-faixa-idade`)
      .toPromise()
      .then(response => {
        const resultado = response;
        return resultado;
      });
  }
  
  porPercentualDeObesos(): Promise<any> {
    return this.http.get<any>(`${this.dashboardUrl}/estatisticas/percentual-obesos`)
      .toPromise()
      .then(response => {
        const resultado = response;
        return resultado;
      });
  }
  
  porTipoSanguineoEMedia(): Promise<any> {
    return this.http.get<any>(`${this.dashboardUrl}/estatisticas/tipo-sanguineo-por-media-de-idade`)
      .toPromise()
      .then(response => {
        const resultado = response;
        return resultado;
      });
  }
  
  quantidadePorTipo(): Promise<any> {
    return this.http.get<any>(`${this.dashboardUrl}/estatisticas/quantidade-por-tipo-sanguineo`)
      .toPromise()
      .then(response => {
        const resultado = response;
        return resultado;
      });
  }

  
}
