import {Component, Input} from '@angular/core';
import {NgIf} from '@angular/common';
import {User} from '../../model/user';

@Component({
  selector: 'app-user-info',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './user-info.component.html',
})
export class UserInfoComponent {
  @Input() userData: User | null = null;
}
