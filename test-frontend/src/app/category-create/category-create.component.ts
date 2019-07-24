import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.scss']
})
export class CategoryCreateComponent implements OnInit {

  @Input() Category = {id: '', name: ''};
  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
  }

  addCategory() {
    this.categoryService.addCategory(this.Category).subscribe((result) => {
      alert(result);
      this.Category = {id: '', name: ''};
    }, (error) => {
      console.log(error);
      alert(error);
    } );
  }
}
