import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Produit } from './Models/Subscription';
@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private url = 'http://localhost:8088/Produit';

  constructor(private http: HttpClient) { }

    AjouterProduit(produit: Produit): Observable<Produit> {
      return this.http.post<Produit>(this.url +"/AjouterProduit", produit,{responseType:'text' as 'json'});
    }
    ModifierProduit(produit: Produit): Observable<Produit> {
      return this.http.put<Produit>(`${this.url}/ModifierProduit`, produit);
    }
  
    SupprimerProduit(idProduit: number): Observable<any> {
      return this.http.delete<any>(`${this.url}/SupprimerProduit/${idProduit}`);
    }
  
    AfficherProduits(): Observable<Produit[]> {
      return this.http.get<Produit[]>(`${this.url}/AffichageProduit`);
    }
  
    RechercheProduit(nom: string): Observable<Produit[]> {
      return this.http.get<Produit[]>(`${this.url}/RechercheProduit?Nom=${nom}`);
    }
  }

