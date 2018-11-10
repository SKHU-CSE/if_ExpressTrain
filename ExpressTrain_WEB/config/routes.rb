Rails.application.routes.draw do
  #제휴점
  get '/index' => 'apply#index'

  get 'apply/new'
  
  post 'apply/create'

  get 'apply/update/:apply_id' => 'apply#update'

  get 'apply/delete'

  #카테고리
  get 'category/create'

  get 'category/delete'

  devise_for :members
  #회원정보 수정하는 라우트
  devise_scope :member do 
    get '/members/sign_out' , to: 'devise/sessions#destroy'
    get '/members/:id/edit' , to: 'members#edit'
    patch '/members/:id', to: 'members#update'
  end
  resources :members, only: [:new, :create, :edit, :update]
  
  root 'main#index'
  
  get '/map'=>'home#map'
 
  get '/list' =>'home#list'
  
  post '/finder' => 'home#list'
  
  get '/finder'=> 'home#list'
  
  get 'main/index'
  
  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
