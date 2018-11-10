class CommentList < ActiveRecord::Base
    belongs_to :menu
    has_many :comments
end
