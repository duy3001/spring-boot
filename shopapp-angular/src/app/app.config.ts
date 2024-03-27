import { ApplicationConfig } from '@angular/core';
import { RouterModule, provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { withFetch } from '@angular/common/http';
import { Provider } from '@angular/core';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { adminRoutes } from './components/admin/admin-routes';
import { HttpClientModule } from '@angular/common/http';

const tokenInterceptorProvider: Provider =
  { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true};

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), 
    provideHttpClient(withFetch()),
    importProvidersFrom(RouterModule.forChild(adminRoutes)),
    tokenInterceptorProvider,
    provideClientHydration(),
    importProvidersFrom(HttpClientModule),
  ]
};
