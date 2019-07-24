import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {Book} from '../models/Book';
import {BookService} from '../services/book.service';

@Component({
  selector: 'app-category-books',
  templateUrl: './category-books.component.html',
  styleUrls: ['./category-books.component.scss']
})
export class CategoryBooksComponent implements OnInit {

  Books: any = {};
  show: boolean ;
  @Input() Category = { id: '', name: ''};
  @Input() searchId = '';

  constructor(private bookService: BookService) {
    this.show = false;
  }

  ngOnInit() {
  }

  searchBooksById() {
    this.Books = [];
    this.bookService.getBooksByCategory(this.searchId).subscribe((data) => {
      this.show = true;
      this.Books = data.data;
    });
  }
}
