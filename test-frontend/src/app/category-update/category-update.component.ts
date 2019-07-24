import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';

@Component({
  selector: 'app-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.scss']
})
export class CategoryUpdateComponent implements OnInit {

  @Input() searchId = '';
  @Input() Category = { id: '', name: ''};

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
  }

  searchCategoryById() {
    this.categoryService.getBooksByCategory(this.searchId).subscribe((result) => {
      this.Category = { id: result.data.id, name: result.data.name};
    }, (err) => {
      console.log(err);
      alert (err);
    });
  }

  updateCategory() {
    this.categoryService.updateCategory(this.Category.id, this.Category).subscribe((result) => {
      alert(result.message);
      this.Category = { id: '', name: ''};
    }, (err) => {
      console.log(err);
    });
  }

  searchCategory() {
    this.categoryService.getCategory(this.searchId).subscribe((result) => {
      this.Category = { id: result.data.id, name: result.data.name};
    }, (err) => {
      console.log(err);
      alert (err);
    });
  }

}
