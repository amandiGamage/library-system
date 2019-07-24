import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';

@Component({
  selector: 'app-category-delete',
  templateUrl: './category-delete.component.html',
  styleUrls: ['./category-delete.component.scss']
})
export class CategoryDeleteComponent implements OnInit {
  @Input() Category = { id: '', name: ''};
  @Input() searchId = '';

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
  }

  searchCategory() {
    this.categoryService.getCategory(this.searchId).subscribe((result) => {
      this.Category = { id: result.data.id, name: result.data.name};
    }, (err) => {
      console.log(err);
      alert (err);
    });
  }

  deleteCategory() {
    this.categoryService.deleteCategory(this.Category.id).subscribe((result) => {
      alert(result);
    }, (error) => {
      alert(error);
    } );
  }
}
