import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';

const endpoint = 'http://localhost:8080/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  addCategory(category): Observable<any> {
    console.log(category);
    return this.http.post<any>(endpoint + 'books/categories', JSON.stringify(category), httpOptions).pipe(
      tap((a) => alert(a.message)),
      catchError(this.handleError<any>('Category'))
    );
  }

  getBooksByCategory(id): Observable<any> {
    return this.http.get(endpoint + 'categories/' + id ).pipe(
      map(this.extractData));
  }

  getCategory(id): Observable<any> {
    return this.http.get(endpoint + 'categories/' + id).pipe(
      map(this.extractData));
  }

  updateCategory(id, category): Observable<any> {
    return this.http.put(endpoint + 'categories/' + id, JSON.stringify(category), httpOptions).pipe(
      tap(_ => alert(`updated category id=${id}`)),
      catchError(this.handleError<any>('updateCategory'))
    );
  }

  deleteCategory(id): Observable<any> {
    return this.http.delete(endpoint + '/books/categories/' + id, httpOptions).pipe(
      tap(_ => console.log(`deleted category id=${id}`)),
      catchError(this.handleError<any>('deleteCategory'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      alert(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private extractData(res: Response) {
    const body = res;
    return body || { };
  }
}
