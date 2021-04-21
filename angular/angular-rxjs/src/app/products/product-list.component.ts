import { ChangeDetectionStrategy, Component } from '@angular/core';

import { BehaviorSubject, combineLatest, EMPTY, Subject } from 'rxjs';
import { catchError, map, startWith, tap } from 'rxjs/operators';
import { ProductCategoryService } from '../product-categories/product-category.service';

import { ProductService } from './product.service';

@Component({
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],

  // This won't notice changes that were made by referencing a property e.g.: this.errorMessage = "Error" will not cause any changes
  // The ui will only update if the observable pushes new data
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductListComponent {
  public pageTitle = 'Product List';
  public errorMessage = '';

  // Previously was Subject.
  // BehaviourSubject lets us define initial values.
  private categorySelectedSubject = new BehaviorSubject<number>(0);
  categorySelectedAction$ = this.categorySelectedSubject.asObservable();

  products$ = combineLatest([
    this.productService.productsWithCategories$,
    this.categorySelectedAction$ // for initial values a pipe can be used: e.g: .pipe(startWith(0))
  ]).pipe(
    // Every time the user selects a category, the items get filtered and pushed into the stream
    map(([products, selectedCategoryId]) => products.filter(product => selectedCategoryId ? product.categoryId === selectedCategoryId : true)),
    catchError(err => {
      this.errorMessage = err;
      return EMPTY;
    })
  );

  categories$ = this.productCategoryService.productCategories$.pipe(
    catchError(err => {
      this.errorMessage = err;
      return EMPTY;
    })
  );

  constructor(private productService: ProductService, 
              private productCategoryService: ProductCategoryService) { }

  onAdd(): void {
    console.log('Not yet implemented');
  }

  onSelected(categoryId: string): void {
    this.categorySelectedSubject.next(+categoryId);
  }
}
