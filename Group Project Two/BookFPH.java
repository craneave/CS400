
/**
 * Frontend Book Place Holder 
 * 
 *
 */
public class BookFPH implements IBook{
        private String title;
        private int Length;
        private String Author;
        private String Genre;

        BookFPH(String title, int ISBN, String Author, String Genre){
                this.title = title;
                this.Length = ISBN;
                this.Author = Author;
                this.Genre = Genre;


        }

		@Override
		public int compareTo(IBook o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLength() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getAuthor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getGenre() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int compareToLength(IBook o) {
			// TODO Auto-generated method stub
			return 0;
		}
}